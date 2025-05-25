package org.study.mini.mapper;

import org.study.mini.dto.InstDto;
import org.study.mini.entity.Inst;

public class InstMapper {
    public static InstDto toDto(Inst entity) {
        return InstDto.builder()
                .instId(entity.getInstId())
                .instName(entity.getInstName())
                .instCode(entity.getInstCode())
                .useYn(entity.getUseYn())
                .build();
    }

    public static Inst toEntity(InstDto dto) {
        return Inst.builder()
                .instId(dto.getInstId())
                .instName(dto.getInstName())
                .instCode(dto.getInstCode())
                .useYn(dto.getUseYn())
                .build();
    }
}