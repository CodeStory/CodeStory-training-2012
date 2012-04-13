package net.gageot;

import com.google.inject.*;
import net.gageot.codestory.*;

import java.util.*;

@Singleton
public class CommitService {
	public List<Commit> listCommits() {
		return Arrays.asList( //
				new Commit("dgageot", "03/01/2012", "Troisieme commit", "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf"), //
				new Commit("jeanlaurent", "02/01/2012", "Deuxieme commit", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e"), //
				new Commit("eric", "01/01/2012", "Premier commit", "https://secure.gravatar.com/avatar/77da98419ae312eb0e322a3dac44a734"));
	}
}
