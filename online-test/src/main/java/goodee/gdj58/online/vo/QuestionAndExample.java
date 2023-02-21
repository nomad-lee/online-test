package goodee.gdj58.online.vo;

import lombok.Data;

@Data
public class QuestionAndExample {
	private int questionNo;
	private int testNo;
	private int questionIdx;
	private String questionTitle;
	private int exampleNo;
	private int exampleIdx;
	private String exampleTitle;
	private String exampleOx;
}
