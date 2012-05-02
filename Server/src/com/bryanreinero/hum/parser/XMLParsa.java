package com.bryanreinero.hum.parser;

public class XMLParsa {

	public enum Tag{
		And("And"),
		AreaCode("AreaCode"),
		Carriers("Carriers"),
		City("City"),
		Compare("Compare"),
		Continent("Continent"),
		Country("Country"),
		DMA("DMA"),
		DecisionTree("DecisionTree"),
		Deterministic("Deterministic"),
		Else("Else"),
		GetCookie("GetCookie"),
		IP("IP"),
		If("If"),
		Input("Input"),
		L1Domain("L1Domain"),
		L2Domain("L2Domain"),
		Language("Language"),
		LiteralNode("LiteralNode"),
		Log("Log"),
		NonDetermintistic("NonDetermintistic"),
		Or("Or"),
		Path("Path"),
		RandomNumber("RandomNumber"),
		Redirect("Redirect"),
		ReferringURL("ReferringURL"),
		RequestBody("RequestBody"),
		RequestHeader("RequestHeader"),
		RequestHost("RequestHost"),
		RequestURL("RequestURL"),
		RequestURLPage("RequestURLPage"),
		RequestURLPath("RequestURLPath"),
		RequestURLProtocol("RequestURLProtocol"),
		ResponseBody("ResponseBody"),
		ResponseCode("ResponseCode"),
		ResponseHeader("ResponseHeader"),
		ResponseHost("ResponseHost"),
		ResponsePage("ResponsePage"),
		ResponseParameter("ResponseParameter"),
		ResponsePath("ResponsePath"),
		ResponsePort("ResponsePort"),
		ResponseProtocol("ResponseProtocol"),
		SetCookie("SetCookie"),
		SetResponseHeader("SetResponseHeader"),
		SetResponseHost("SetResponseHost"),
		SetResponseParameter("SetResponseParameter"),
		SetResponseURL("SetResponseURL"),
		SetVariable("SetVariable"),
		State("State"),
		StringReplace("StringReplace"),
		Target("Target"),
		Terma("Terma"),
		Termb("Termb"),
		UserAgent("UserAgent"),
		Value("Value"),
		ZipCode("ZipCode");
		
		private final String value;

        Tag(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static Tag fromValue(String v) {
            for (Tag c: Tag.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

	}
}
