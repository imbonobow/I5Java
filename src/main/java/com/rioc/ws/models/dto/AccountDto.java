package com.rioc.ws.models.dto;

import com.rioc.ws.models.dao.BankDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto
{
	@NotBlank
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	private String firstName;

	@NotBlank
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)

	private String lastName;
	private AddressDto address;
	private List<BankDetailDto> bankDetails;

}
