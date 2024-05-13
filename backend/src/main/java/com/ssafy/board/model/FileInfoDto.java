package com.ssafy.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "FileInfoDto : 업로드 파일정보", description = "게시글에 업로드한 파일의 정보를 나타낸다.")
public class FileInfoDto {

	@Schema(description = "저장폴더")
	private String saveFolder;
	@Schema(description = "원본 파일이름")
	private String originalFile;
	@Schema(description = "저장 파일이름")
	private String saveFile;
	
}
