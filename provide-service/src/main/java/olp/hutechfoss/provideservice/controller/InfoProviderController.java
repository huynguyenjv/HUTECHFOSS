package olp.hutechfoss.provideservice.controller;

import lombok.RequiredArgsConstructor;
import olp.hutechfoss.provideservice.dto.ApiResponse;
import olp.hutechfoss.provideservice.dto.InfoProviderDto;
import olp.hutechfoss.provideservice.service.InfoProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info-provider")
@RequiredArgsConstructor
public class InfoProviderController {

    private final InfoProviderService infoProviderService;
    private final Logger logger = LoggerFactory.getLogger(InfoProviderController.class);

    @PostMapping("/create")
    public ApiResponse<InfoProviderDto> create(@RequestBody InfoProviderDto request) {
        logger.info("Create info provider request:{}", request);
        return ApiResponse.<InfoProviderDto>builder()
                .code(200)
                .data(infoProviderService.create(request))
                .build();
    }

    @PostMapping("/update/{id}")
    public ApiResponse<InfoProviderDto> update(@RequestParam("id") Long Id, @RequestBody InfoProviderDto request) {
        logger.info("Update info provider request:{}", request);
        return ApiResponse.<InfoProviderDto>builder()
                .code(200)
                .data(infoProviderService.update(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<InfoProviderDto> getEntity(@PathVariable("id") Long id) {
        logger.info("Get info provider request:{}", id);
        return ApiResponse.<InfoProviderDto>builder()
                .code(200)
                .data(infoProviderService.getEntity(id))
                .build();
    }

    @GetMapping("/")
    public ApiResponse<List<InfoProviderDto>> getEntities() {
        logger.info("Get list info provider request");
        return ApiResponse.<List<InfoProviderDto>>builder()
                .code(200)
                .data(infoProviderService.getEntities())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<InfoProviderDto> delete(@PathVariable Long id) {
        logger.info("Delete info provider request:{}", id);
        return ApiResponse.<InfoProviderDto>builder()
                .code(200)
                .data(infoProviderService.delete(id))
                .build();
    }




}
