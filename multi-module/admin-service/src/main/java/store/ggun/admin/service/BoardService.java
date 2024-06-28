package store.ggun.admin.service;
import store.ggun.admin.domain.model.BoardModel;
import store.ggun.admin.domain.dto.BoardDto;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {




    default BoardModel dtoToEntity(BoardDto dto){

        return BoardModel.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    default BoardDto entityToDto(BoardModel ent){

        return BoardDto.builder()
                .id(ent.getId())
                .title(ent.getTitle())
                .description(ent.getDescription())
                .regDate(ent.getRegDate().toString())
                .modDate(ent.getModDate().toString())
                .build();
    }
}
