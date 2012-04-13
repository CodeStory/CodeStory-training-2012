import com.google.common.base.*
import com.google.common.collect.*
import com.google.common.util.concurrent.*
import net.gageot.codestory.*

AtomicLongMap results = AtomicLongMap.create();
for (Commit commit : allCommits) {
    results.getAndIncrement(commit.login);
}

return Ordering.natural().onResultOf(Functions.forMap(results.asMap())).max(results.asMap().keySet());
