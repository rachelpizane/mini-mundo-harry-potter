package edu.rachel.dto;

import edu.rachel.enums.AppStatusEnum;

public record AppResponse<T>(AppStatusEnum status, T resposta, String messageErro) {
}
