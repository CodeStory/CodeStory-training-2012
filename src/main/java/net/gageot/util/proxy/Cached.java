package net.gageot.util.proxy;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Cached {
}
