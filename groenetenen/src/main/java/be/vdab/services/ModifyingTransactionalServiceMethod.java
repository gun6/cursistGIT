package be.vdab.services;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Retention(RUNTIME)
@Target(METHOD)
@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
public @interface ModifyingTransactionalServiceMethod {

}
