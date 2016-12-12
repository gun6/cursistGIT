package be.vdab.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy=PostcodeReeksVanKleinerDanOfGelijkAanTotValidator.class)
public @interface PostcodeReeksVanKleinerDanOfGelijkAanTot {
	String message() default "{be.vdab.constraints.VanKleinerDanOfGelijkAanTot}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
