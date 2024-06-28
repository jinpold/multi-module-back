//package store.ggun.user.serviceImpl;
//
//import store.ggun.user.domain.BoardDto;
//import store.ggun.user.domain.BoardModel;
//import store.ggun.user.repository.BoardRepository;
//import store.ggun.user.domain.Messenger;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import store.ggun.user.service.BoardService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class BoardServiceImpl implements BoardService {
//
//    private final BoardRepository repository;
//    @Override
//    public Messenger save(BoardDto board) {
//        BoardModel boardModel = dtoToModel(board);
//        if (repository.existsByTitle(board.getTitle())){
//            return Messenger.builder()
//                    .message("이미 존재하는 게시판 입니다.")
//                    .build();
//        }
//        repository.save(boardModel);
//        return Messenger.builder()
//                .message(repository.existsByTitle(board.getTitle())?"SUCCESS":"FAIL")
//                .build();
//    }
//
//    @Override
//    public Messenger modify(BoardDto board) {
//        BoardModel boardModel = dtoToModel(board);
//        repository.save(boardModel);
//        return Messenger.builder()
//                .message(repository.findById(boardModel.getId()).orElseThrow().equals(boardModel)?"SUCCESS":"FAIL")
//                .build();
//    }
//
//    @Override
//    public Messenger delete(Long id) {
//        repository.deleteById(id);
//        return Messenger.builder()
//                .message(repository.existsById(id)?"FAIL":"SUCCESS")
//                .build();
//    }
//
//
//}
