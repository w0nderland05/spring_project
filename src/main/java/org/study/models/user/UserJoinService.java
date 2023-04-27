package org.study.models.user;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.constants.UserRole;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.user.UserJoin;
import org.study.controllers.user.UserJoinValidator;
import org.study.entities.User;
import org.study.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class UserJoinService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final UserJoinValidator validator;

    public void join(UserJoin join){ join(join,null);}

    public void join(UserJoin join,Errors errors){

        if (errors != null && errors.hasErrors()) {
            return;
        }

        validator.validate(join,errors);

        String hash = passwordEncoder.encode(join.getUserPw());

        User user = User.builder()
                .userEmail(join.getUserEmail())
                .userPw(hash)
                .userNm(join.getUserNm())
                .userNickNm(join.getUserNickNm())
                .cellPhone(join.getCellphone())
                .gender(join.getGender())
                .role(UserRole.USER) //기본
                .birth(join.getBirth())
                .build();

        repository.save(user);


    }
}
