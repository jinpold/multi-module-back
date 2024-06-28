package store.ggun.user.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.ggun.user.domain.QUserModel;
import store.ggun.user.domain.UserDto;
import store.ggun.user.domain.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{
    private final JPAQueryFactory queryFactory;
    private final QUserModel qUser = QUserModel.userModel;

    @Transactional
    @Override
    public void modify(UserDto userDto) {
        JPAUpdateClause updateClause = queryFactory.update(qUser);

        if (userDto.getPassword() != null) {
            updateClause.set(qUser.password, userDto.getPassword());
        }
        if (userDto.getName() != null) {
            updateClause.set(qUser.name, userDto.getName());
        }
        if (userDto.getAge() != null) {
            updateClause.set(qUser.age, userDto.getAge());
        }
        if (userDto.getSex() != null) {
            updateClause.set(qUser.sex, userDto.getSex());
        }
        if (userDto.getEmail() != null) {
            updateClause.set(qUser.email, userDto.getEmail());
        }
        if (userDto.getSsnF() != null) {
            updateClause.set(qUser.ssnF, userDto.getSsnF());
        }
        if (userDto.getSsnS() != null) {
            updateClause.set(qUser.ssnS, userDto.getSsnS());
        }
        if (userDto.getAddress() != null) {
            updateClause.set(qUser.address, userDto.getAddress());
        }
        if (userDto.getPhone() != null) {
            updateClause.set(qUser.phone, userDto.getPhone());
        }
        if (userDto.getColor() != null) {
            updateClause.set(qUser.color, userDto.getColor());
        }
        if (userDto.getInvestmentPropensity() != null) {
            updateClause.set(qUser.investmentPropensity, userDto.getInvestmentPropensity());
        }

        updateClause.where(qUser.id.eq(userDto.getId()))
                .execute();
    }

    @Override
    public UserModel modifyFind(UserDto userDto) {
        JPAQuery<UserModel> query = queryFactory.selectFrom(qUser);
        query.where(qUser.id.eq(userDto.getId()));
        List<UserModel> results = query.fetch();
        UserModel user = new UserModel();
        user.setId(userDto.getId());
        user.setUsername(null);
        user.setPassword(userDto.getPassword() != null?results.get(0).getPassword():null);
        user.setName(userDto.getName() != null?results.get(0).getName():null);
        user.setAge(userDto.getAge() != null?results.get(0).getAge():null);
        user.setSex(userDto.getSex() != null?results.get(0).getSex():null);
        user.setEmail(userDto.getEmail() != null?results.get(0).getEmail():null);
        user.setSsnF(userDto.getSsnF() != null?results.get(0).getSsnF():null);
        user.setSsnS(userDto.getSsnS() != null?results.get(0).getSsnS():null);
        user.setAddress(userDto.getAddress() != null?results.get(0).getAddress():null);
        user.setPhone(userDto.getPhone() != null?results.get(0).getPhone():null);
        user.setColor(userDto.getColor() != null?results.get(0).getColor():null);
        user.setInvestmentPropensity(userDto.getInvestmentPropensity() != null?results.get(0).getInvestmentPropensity():null);
        log.info("repository{}", user);
        return user;
    }
}