package com.jean.blue.web.api;

import com.jean.blue.service.FeesService;
import com.jean.blue.web.dto.fees.FeesListRequestDto;
import com.jean.blue.web.dto.fees.FeesResponseDto;
import com.jean.blue.web.dto.fees.FeesSaveRequestDto;
import com.jean.blue.web.dto.fees.FeesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FeesApiController {

    private final FeesService feesService;

    @PostMapping("/fees")
    public Long save(@RequestBody FeesSaveRequestDto requestDto){
        Long returnId = feesService.save(requestDto);
        return returnId;
    }

    @PutMapping("/fees/update/{feesId}")
    public Long update(@PathVariable Long feesId, @RequestBody FeesUpdateRequestDto requestDto) {
        return feesService.update(feesId, requestDto);
    }

    @PutMapping("/fees/delete/{feesId}")
    public Long delete(@PathVariable Long feesId,  @RequestBody FeesUpdateRequestDto requestDto) {
        return feesService.delete(feesId, requestDto);
    }

    @GetMapping("/fees")
    public List<FeesListRequestDto> fees() {
        List<FeesListRequestDto> feesListRequestDto = feesService.findAllDesc();
        return feesListRequestDto;
    }

    @GetMapping("/fees/{feesId}")
    public FeesResponseDto findById(@PathVariable Long feesId) {
        return feesService.findById(feesId);
    }

}
