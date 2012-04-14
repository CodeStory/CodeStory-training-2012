package net.gageot;

import com.google.common.base.*;
import com.google.inject.*;
import net.gageot.codestory.Commit;
import net.gageot.util.dates.*;
import org.eclipse.egit.github.core.*;
import org.lesscss.*;

import javax.activation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;

import static com.google.common.base.Objects.*;
import static net.gageot.listmaker.ListMaker.*;

@Path("/")
public class CodeStoryResource {
	@Inject AllCommits allCommits;
	@Inject AllBadges allBadges;

	@GET
	public Response index() {
		return Response.temporaryRedirect(URI.create("index.html")).build();
	}

	@GET
	@Path("commits.json")
	@Produces("application/json;charset=UTF-8")
	public Iterable<Commit> commits() {
		return with(allCommits.list()).to(TO_COMMIT);
	}

	@GET
	@Path("topCommiter.json")
	@Produces("application/json;charset=UTF-8")
	public User topCommiter() {
		return allBadges.topCommiter();
	}

	@GET
	@Path("{path : .*\\.less}")
	public String less(@PathParam("path") String path) throws IOException, LessException {
		return new LessCompiler().compile(new File("web", path));
	}

	@GET
	@Path("{path : .*}")
	public Response staticResource(@PathParam("path") String path) {
		File file = new File("web", path);
		if (!file.exists()) {
			throw new WebApplicationException(404);
		}
		String mimeType = new MimetypesFileTypeMap().getContentType(file);
		return Response.ok(file, mimeType).build();
	}

	private static Function<RepositoryCommit, Commit> TO_COMMIT = new Function<RepositoryCommit, Commit>() {
		@Override
		public Commit apply(RepositoryCommit commit) {
			User author = firstNonNull(commit.getAuthor(), new User().setAvatarUrl("").setLogin(""));

			String date = DateFormats.format(commit.getCommit().getAuthor().getDate());
			String message = commit.getCommit().getMessage();
			String login = author.getLogin();
			String avatarUrl = author.getAvatarUrl();

			return new Commit(login, date, message, avatarUrl);
		}
	};
}
