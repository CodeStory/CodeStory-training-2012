package net.gageot.codestory;

import com.google.inject.*;
import com.google.inject.name.*;
import lombok.*;

@Data
public class Project {
	private final String userName;
	private final String project;

	@Inject
	public Project(@Named("username") String userName, @Named("project") String project) {
		this.userName = userName;
		this.project = project;
	}
}
