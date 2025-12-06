package com.luis.apprest.dto;

import com.luis.apprest.model.CheckListItem;

public record CheckListItemResponse(
        Long id,
        String text,
        boolean checked
) {
    // Static factory method
    public static CheckListItemResponse of(CheckListItem item) {
        return new CheckListItemResponse(
                item.getId(),
                item.getText(),
                item.isChecked()
        );
    }

}
