package olp.hutechfoss.provideservice.service;

import lombok.RequiredArgsConstructor;
import olp.hutechfoss.provideservice.Repository.InfoProvideRepository;
import olp.hutechfoss.provideservice.dto.InfoProviderDto;
import olp.hutechfoss.provideservice.entity.InfoProvider;
import olp.hutechfoss.provideservice.error.ErrorCode;
import olp.hutechfoss.provideservice.exception.AppException;
import olp.hutechfoss.provideservice.mapper.InfoProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoProviderService {
    private final InfoProvideRepository infoProvideRepository;
    private final InfoProviderMapper infoProviderMapper;
    private final Logger logger = LoggerFactory.getLogger(InfoProviderService.class);

    public InfoProviderDto create(InfoProviderDto request) {
        logger.info("Create InfoProviderRequest:{}", request);
        InfoProvider saveEntity = infoProviderMapper.toEntity(request);
        infoProvideRepository.save(saveEntity);

        return infoProviderMapper.toDto(saveEntity);
    }

    public InfoProviderDto update(InfoProviderDto  request) {
        logger.info("Update InfoProviderRequest:{}", request);
        if(request.getId() != null) {
            throw new AppException(ErrorCode.OBJECT_IS_EXIST);
        }
        InfoProvider saveEntity = infoProviderMapper.toEntity(request);
        infoProvideRepository.save(saveEntity);

        return infoProviderMapper.toDto(saveEntity);
    }

    public InfoProviderDto getEntity(Long id){
        logger.info("Get InfoProviderRequest:{}", id);
        if(id == null) {
            throw new AppException(ErrorCode.OBJECT_IS_EXIST);
        }
        InfoProvider entity = infoProvideRepository.findById(id).orElse(null);

        return infoProviderMapper.toDto(entity);
    }

    public List<InfoProviderDto> getEntities(){
        logger.info("Get InfoProviderRequest");
        List<InfoProvider> entityList = infoProvideRepository.findAll();
        if(entityList.isEmpty()) {
            throw new AppException(ErrorCode.DATA_NOT_FOUND);
        }
        return infoProviderMapper.toDto(entityList);
    }

    public InfoProviderDto delete(Long id) {
        logger.info("Delete InfoProviderRequest:{}", id);
        if(id == null) {
            throw new AppException(ErrorCode.DATA_NOT_FOUND);
        }
        InfoProvider deleteEntity = infoProvideRepository.findById(id).orElse(null);

        infoProvideRepository.deleteById(id);

        return infoProviderMapper.toDto(deleteEntity);
    }
}
