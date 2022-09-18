package ua.app.ustil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.app.models.AppUser;
import ua.app.services.UserDetailService;

@Component
public class AppUserValidator implements Validator {

    private final UserDetailService userDetailService;

    @Autowired
    public AppUserValidator(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AppUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUser user = (AppUser) target;

        try {
            userDetailService.loadUserByUsername(user.getUserName());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("userName", "", "User with this name already exists!!!");
    }
}
