//package store.ggun.user.service;
//
//import store.ggun.user.domain.BoardDto;
//import store.ggun.user.domain.BoardModel;
//import store.ggun.user.domain.Messenger;
//
//public interface BoardService {
//    Messenger save(BoardDto board);
//
//    Messenger delete(Long id);
//
//    Messenger modify(BoardDto board);
//
//    default BoardModel dtoToModel(BoardDto dto) {
//        return BoardModel.builder()
//                .id(dto.getId())
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .description(dto.getDescription())
//                .build();
//    }
//
//    default BoardDto modelToDto(BoardModel model) {
//        return BoardDto.builder()
//                .id(model.getId())
//                .title(model.getTitle())
//                .content(model.getContent())
//                .description(model.getDescription())
//                .build();
//    }
//}
