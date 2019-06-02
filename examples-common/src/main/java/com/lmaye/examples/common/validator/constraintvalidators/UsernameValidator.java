package com.lmaye.examples.common.validator.constraintvalidators;

import com.lmaye.examples.common.validator.constraints.Username;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * -- 用户名校验
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public class UsernameValidator implements ConstraintValidator<Username, String> {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("(?!_)\\w{2,32}");

    /**
     * 校验用户名
     *
     * @param username 用户名
     * @return 通过返回true，不通过返回false
     */
    public static boolean isUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    @Override
    public void initialize(Username constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isBlank(value) || isUsername(value);
    }
}
