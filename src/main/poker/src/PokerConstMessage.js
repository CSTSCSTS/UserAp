import * as PokerConstNumber from './PokerConstNumber.js';

const userNameNotInputMessage = "ユーザー名が未入力です。";
const passwordNotInputMessage = "パスワードが未入力です。";
const confirmationPasswordNotInputMessage = "パスワード(確認)が未入力です。";
const userNameExceedMaxLengthMessage = "ユーザー名が" + PokerConstNumber.USER_NAME_MAX_LENGTH + "文字を超えています。";
const passwordExceedMaxLengthMessage = "パスワードが" + PokerConstNumber.PASSWORD_MAX_LENGTH + "文字を超えています。";
const passwordNotMatchMessage = "パスワードとパスワード(確認)が一致していません。";
const betMoneyNotInputMessage = "ベット額が未入力です。";
const betMoneyisZeroMessage =  "ベット額は1円以上になるように入力してください。";
const betMoneyExpectNumberMessage = "数字以外が入力されています。";

export {
	userNameNotInputMessage,
	passwordNotInputMessage,
	confirmationPasswordNotInputMessage,
	userNameExceedMaxLengthMessage,
	passwordExceedMaxLengthMessage,
	passwordNotMatchMessage,
	betMoneyNotInputMessage,
	betMoneyisZeroMessage,
	betMoneyExpectNumberMessage
};