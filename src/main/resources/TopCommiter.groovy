commits.countBy { it.login }.max { it.value }.key
