package net.gageot;

import com.google.common.base.*;
import com.google.inject.*;
import net.gageot.codestory.*;
import net.gageot.codestory.Commit;
import net.gageot.util.dates.*;
import org.eclipse.egit.github.core.*;
import org.lesscss.*;

import javax.activation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;
import java.util.*;

import static com.google.common.base.Objects.*;
import static com.google.common.collect.FluentIterable.*;
import static java.util.Arrays.*;

@Path("/")
public class CodeStoryResource {
	@Inject Project project;
	@Inject AllCommits allCommits;
	@Inject AllBadges allBadges;

	@GET
	public Response index() {
		return Response.temporaryRedirect(URI.create("index.html")).build();
	}

	@GET
	@Path("project")
	@Produces("application/json;charset=UTF-8")
	public Project project() {
		return project;
	}

	@GET
	@Path("commits")
	@Produces("application/json;charset=UTF-8")
	public Iterable<Commit> commits() {
		return from(allCommits.list()).transform(TO_COMMIT);
	}

	@GET
	@Path("badges")
	@Produces("application/json;charset=UTF-8")
	public List<Badge> badges() {
		return asList( //
				toBadge(allBadges.topCommiter(), "topCommiter", "Top Commiter"), //
				toBadge(allBadges.mostVerboseCommitter(), "verboseCommiter", "Verbose Committer") //
		);
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

	private static Badge toBadge(User user, String filename, String text) {
		String date = DateFormats.format(new Date());

		return new Badge(date, filename, text, user.getAvatarUrl());
	}
}
