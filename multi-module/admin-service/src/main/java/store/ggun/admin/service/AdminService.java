package store.ggun.admin.service;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.model.AdminModel;
import store.ggun.admin.domain.dto.AdminDto;
import java.util.Optional;

public interface AdminService extends CommandService<AdminDto>, QueryService<AdminDto> {
    // command
    Messenger modify(AdminDto adminDto);
    Messenger modifyRole(AdminDto adminDto);
    Messenger update(AdminDto adminDto);
    // query
    Messenger login(AdminDto param);
    Boolean existsByUsername(String username);
    Optional<AdminModel> findAdminByRole(String role);
    Optional<AdminModel> findAdminByUsername(String enpName);
    Boolean logout(String accessToken);
    Optional<AdminDto> findUserInfo(String username);

    default AdminModel dtoToEntity(AdminDto dto){
        return AdminModel.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .enpName(dto.getEnpName())
                .enpNum(dto.getEnpNum())
                .department(dto.getDepartment())
                .position(dto.getPosition())
                .job(dto.getJob())
                .enpEmail(dto.getEnpEmail())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .token(dto.getToken())
                .build();
    }


    default AdminDto entityToDto(AdminModel ent) {
        return AdminDto.builder()
                .id(ent.getId())
                .username(ent.getUsername())
                .password(ent.getPassword())
                .enpName(ent.getEnpName())
                .enpNum(ent.getEnpNum())
                .department(ent.getDepartment())
                .position(ent.getPosition())
                .job(ent.getJob())
                .enpEmail(ent.getEnpEmail())
                .phone(ent.getPhone())
                .role(ent.getRole())
                .token(ent.getToken())
                .build();
    }



}


