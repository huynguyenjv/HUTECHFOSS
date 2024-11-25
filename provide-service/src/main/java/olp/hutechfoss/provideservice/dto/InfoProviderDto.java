package olp.hutechfoss.provideservice.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfoProviderDto {
    private Long id;
    private String name;
    private String location;
    private String address;
}
