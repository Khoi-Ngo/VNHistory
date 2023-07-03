package com.swp.vnhistory.model;

import java.time.LocalDate;

public enum DynastyEnum {
	DINH("Triều Đinh", "Triều đại Đinh (968-980) là triều đại đầu tiên của nhà Đinh trên lãnh thổ nước Đại Cồ Việt.",
			968, 980),
	LE_SO("Triều Lê Sơ", "Triều đại Lê Sơ (980-1009) là triều đại đầu tiên của nhà Lê trên lãnh thổ nước Đại Cồ Việt.",
			980, 1009),
	LY("Triều Lý", "Triều đại Lý (1009-1225) là triều đại có thời gian tồn tại lâu nhất trong lịch sử nước ta.", 1009,
			1225),
	TRAN("Triều Trần", "Triều đại Trần (1225-1400) là triều đại được xem là kỳ quan của lịch sử nước ta.", 1225, 1400),
	HO("Triều Hồ",
			"Triều đại Hồ (1400-1407) là triều đại ngắn ngủi nhưng có những cống hiến quan trọng cho sự phát triển của nước ta.",
			1400, 1407),
	LE_TRA("Triều Lê Trà",
			"Triều đại Lê Trà (1428-1527) là triều đại cuối cùng của nhà Lê trước khi bị xâm lược bởi quân Minh.", 1428,
			1527),
	NGUYEN("Triều Nguyễn", "Triều đại Nguyễn (1802-1945) là triều đại cuối cùng của Việt Nam trước khi thuộc địa hoá.",
			1802, 1945),
	ANTI_FRENCH("Thời kỳ chống Pháp", "Thời kỳ chống Pháp (1945-1954) là giai đoạn chiến đấu chống lại thực dân Pháp.",
			1945, 1954),
	ANTI_US("Thời kỳ chống Mỹ",
			"Thời kỳ chống Mỹ (1954-1975) là giai đoạn chiến đấu chống lại cuộc chiến tranh Việt Nam của Mỹ.", 1954,
			1975),
	MODERN("Thời hiện đại",
			"Thời kỳ hiện đại (1975-nay) là giai đoạn phát triển và thay đổi sau cuộc chiến tranh Việt Nam.", 1975, 0);

	private String dynastyName;
	private String dynastyDescription;
	private int startYear;
	private int endYear;

	DynastyEnum(String dynastyName, String dynastyDescription, int startYear, int endYear) {
		this.dynastyName = dynastyName;
		this.dynastyDescription = dynastyDescription;
		this.startYear = startYear;
		this.endYear = endYear;
	}

	public String getDynastyName() {
		return dynastyName;
	}

	public String getDynastyDescription() {
		return dynastyDescription;
	}

	public int getStartYear() {
		return startYear;
	}

	public int getEndYear() {
		if (endYear == 0) {
			// Nếu là thời hiện đại, trả về năm hiện tại
			return LocalDate.now().getYear();
		} else {
			return endYear;
		}
	}
}
