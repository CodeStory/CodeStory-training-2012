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
		return from(allCommits.list()).transform(TO_COMMIT);
	}

	@GET
	@Path("badges.json")
	@Produces("application/json;charset=UTF-8")
	public List<Badge> badges() {
		return asList(toBadge(allBadges.topCommiter(), "topCommiter"), toBadge(allBadges.mostVerboseCommitter(), "verboseCommiter"));
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

	private Badge toBadge(User user, String badgeName) {
		String date = DateFormats.format(new Date());
		return new Badge(date, badgeName, user.getLogin(), user.getAvatarUrl());
	}

}
