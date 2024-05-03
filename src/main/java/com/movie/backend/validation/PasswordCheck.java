// package com.gora.backend.validation;

// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;

// import jakarta.validation.Constraint;

// @Target(ElementType.FIELD)
// @Retention(RetentionPolicy.RUNTIME)
// @Constraint(validatedBy = PasswordCheckValidation.class)
// public @interface PasswordCheck {

//     String message() default "비밀번호 형식이 틀립니다.";

//     Class[] groups() default {};

//     Class[] payload() default {};
// }