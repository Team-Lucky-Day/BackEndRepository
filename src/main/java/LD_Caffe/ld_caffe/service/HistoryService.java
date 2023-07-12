package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.HistoryEntity;
import LD_Caffe.ld_caffe.dto.HistoryDto;
import LD_Caffe.ld_caffe.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void saveHistory(HistoryDto historyDto) {
            HistoryEntity historyEntity = HistoryEntity.toHistoryEntity(historyDto);
            historyRepository.save(historyEntity);
    }

    public List<HistoryEntity> getHistory(String userId){
            return historyRepository.findAllByUserId(userId);
        }


}


