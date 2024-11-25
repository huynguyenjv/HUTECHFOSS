package olp.hutechfoss.provideservice.mapper;

import olp.hutechfoss.provideservice.dto.InfoProviderDto;
import olp.hutechfoss.provideservice.entity.InfoProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoProviderMapper extends EntityMapper<InfoProviderDto,InfoProvider> {

}
