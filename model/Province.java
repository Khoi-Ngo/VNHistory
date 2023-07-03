package com.swp.vnhistory.model;

public enum Province {
    HA_NOI("Hà Nội", "Capital city of Vietnam"),
    HA_GIANG("Hà Giang", "Mountainous province in the north"),
    CAO_BANG("Cao Bằng", "Border province in the northeast"),
    BAC_KAN("Bắc Kạn", "Mountainous province in the northeast"),
    TUYEN_QUANG("Tuyên Quang", "Mountainous province in the northeast"),
    LANG_SON("Lạng Sơn", "Border province in the northeast"),
    QUANG_NINH("Quảng Ninh", "Coastal province in the northeast"),
    BAC_GIANG("Bắc Giang", "Province in the northeast"),
    PHU_THO("Phú Thọ", "Province in the northeast"),
    VINH_PHUC("Vĩnh Phúc", "Province in the northeast"),
    BAC_NINH("Bắc Ninh", "Province in the northeast"),
    HAI_DUONG("Hải Dương", "Province in the Red River Delta"),
    HAI_PHONG("Hải Phòng", "Major port city in the northeast"),
    HUNG_YEN("Hưng Yên", "Province in the Red River Delta"),
    THAI_BINH("Thái Bình", "Province in the Red River Delta"),
    HA_NAM("Hà Nam", "Province in the Red River Delta"),
    NAM_DINH("Nam Định", "Province in the Red River Delta"),
    NINH_BINH("Ninh Bình", "Province in the Red River Delta"),
    THANH_HOA("Thanh Hóa", "Province in the north-central region"),
    NGHE_AN("Nghệ An", "Province in the north-central region"),
    HA_TINH("Hà Tĩnh", "Province in the north-central region"),
    QUANG_BINH("Quảng Bình", "Province in the north-central region"),
    QUANG_TRI("Quảng Trị", "Province in the north-central region"),
    THUA_THIEN_HUE("Thừa Thiên Huế", "Province in the north-central region"),
    DA_NANG("Đà Nẵng", "Major city in central Vietnam"),
    QUANG_NAM("Quảng Nam", "Province in central Vietnam"),
    QUANG_NGAI("Quảng Ngãi", "Province in central Vietnam"),
    BINH_DINH("Bình Định", "Province in central Vietnam"),
    PHU_YEN("Phú Yên", "Province in central Vietnam"),
    KHANH_HOA("Khánh Hòa", "Province in central Vietnam"),
    NINH_THUAN("Ninh Thuận", "Province in central Vietnam"),
    BINH_THUAN("Bình Thuận", "Province in central Vietnam"),
    KON_TUM("Kon Tum", "Province in the Central Highlands"),
    GIA_LAI("Gia Lai", "Province in the Central Highlands"),
    DAK_LAK("Đắk Lắk", "Province in the Central Highlands"),
    DAK_NONG("Đắk Nông", "Province in the Central Highlands"),
    LAM_DONG("Lâm Đồng", "Province in the Central Highlands"),
    BINH_PHUOC("Bình Phước", "Province in the southeast"),
    TAY_NINH("Tây Ninh", "Province in the southeast"),
    BINH_DUONG("Bình Dương", "Province in the southeast"),
    DONG_NAI("Đồng Nai", "Province in the southeast"),
    HO_CHI_MINH("Hồ Chí Minh", "Largest city in Vietnam"),
    LONG_AN("Long An", "Province in the Mekong Delta"),
    TIEN_GIANG("Tiền Giang", "Province in the Mekong Delta"),
    BEN_TRE("Bến Tre", "Province in the Mekong Delta"),
    TRA_VINH("Trà Vinh", "Province in the Mekong Delta"),
    VINH_LONG("Vĩnh Long", "Province in the Mekong Delta"),
    DONG_THAP("Đồng Tháp", "Province in the Mekong Delta"),
    AN_GIANG("An Giang", "Province in the Mekong Delta"),
    KIEN_GIANG("Kiên Giang", "Province in the Mekong Delta"),
    CA_MAU("Cà Mau", "Province in the Mekong Delta"),
    BAC_LIEU("Bạc Liêu", "Province in the Mekong Delta"),
    SOC_TRANG("Sóc Trăng", "Province in the Mekong Delta");

    private final String name;
    private final String description;

    private Province(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
