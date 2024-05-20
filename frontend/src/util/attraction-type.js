import { mdiCameraMarker ,mdiPalette,mdiPartyPopper ,mdiSilverwareForkKnife,mdiBed,mdiShopping,mdiBicycle,mdiMapMarkerPath   } from "@mdi/js";
const attrTypeConverter = {
  12: "관광지",
  14: "문화시설",
  15: "축제공연행사",
  25: "여행코스",
  28: "레포츠",
  32: "숙박",
  38: "쇼핑",
  39: "음식점",
  관광지: 12,
  문화시설: 14,
  축제공연행사: 15,
  여행코스: 25,
  레포츠: 28,
  숙박: 32,
  쇼핑: 38,
  음식점: 39,
};

const attrTypes = {
  12: { title: "관광지", icon: mdiCameraMarker  },
  14: {title :"문화시설", icon:mdiPalette },
  15: {title :"축제공연행사", icon:mdiPartyPopper },
  25: {title :"여행코스", icon:mdiMapMarkerPath },
  28: {title :"레포츠", icon:mdiBicycle },
  32: {title :"숙박", icon:mdiBed},
  38: {title :"쇼핑", icon:mdiShopping},
  39: {title :"음식점", icon:mdiSilverwareForkKnife},
};

export { attrTypeConverter, attrTypes };
