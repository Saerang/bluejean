package com.jean.blue.service;

import com.jean.blue.domain.Fees;
import com.jean.blue.domain.Member;
import com.jean.blue.repository.FeesRepository;
import com.jean.blue.repository.MemberRepository;
import com.jean.blue.web.dto.fees.FeesListRequestDto;
import com.jean.blue.web.dto.fees.FeesResponseDto;
import com.jean.blue.web.dto.fees.FeesSaveRequestDto;
import com.jean.blue.web.dto.fees.FeesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeesService {
    private final FeesRepository feesRepository;
    private final MemberService memberService;

    @Transactional
    public Long save(FeesSaveRequestDto requestDto) {
//        Member member = memberService.findMemberByMemberId(requestDto.getMemberId());
//        requestDto.setMember(member);
        return feesRepository.save(requestDto.toEntity()).getFeesId();
    }

    @Transactional
    public Long update(Long feesId, FeesUpdateRequestDto requestDto) {
        Fees fees = feesRepository.findById(feesId)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + feesId));
        requestDto.setMember(requestDto.getMemberId());
        fees.update(requestDto.getMember(), requestDto.getFee(), requestDto.getFeeYear(), requestDto.getFeeMonth());
        return feesId;
    }

    @Transactional
    public Long delete(Long feesId, FeesUpdateRequestDto requestDto) {
        Fees fees = feesRepository.findById(feesId)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + feesId));
        fees.delete(requestDto.getIsDeleted());
        return feesId;
    }

    public FeesResponseDto findById(Long feesId){
        Fees entity = feesRepository.findById(feesId)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id=" + feesId));
        return new FeesResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<FeesListRequestDto> findAllDesc(){
        return feesRepository.findAllDesc().stream()
                .map(FeesListRequestDto::new)
                .collect(Collectors.toList());
    }

}
