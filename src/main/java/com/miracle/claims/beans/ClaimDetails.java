package com.miracle.claims.beans;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDetails {
    private String totalClaimedAmount;
    private String totalPaidAmount;
    private String totalCount;
}
