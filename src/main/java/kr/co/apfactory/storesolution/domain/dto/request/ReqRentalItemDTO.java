package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.RentalItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqRentalItemDTO {
    private Long rentalItemId;
    private String name;
    private Integer size;
    private String memo;

    public RentalItem toRentalItemEntity() {
        return RentalItem.builder()
                .name(this.name)
                .size(this.size)
                .memo(this.memo)
                .build();
    }
}
