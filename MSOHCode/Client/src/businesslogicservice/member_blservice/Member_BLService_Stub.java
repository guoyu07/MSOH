package businesslogicservice.member_blservice;

import java.util.Date;

import util.MemberType;
import util.ResultMessage;

/**
 * @author Pxr created:2016.10.14 latest modify:2016.10.15
 */
public class Member_BLService_Stub implements Member_BLService {
	// 会员等级信息
	MemberLevelVO memberLevelVO;
	// 用户信息
	CustomerVO customerVO;
	// 会员信息
	MemberVO memberVO;

	/**
	 * 会员注册
	 */
	@Override
	public ResultMessage signUp(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO) {
		// 若用户信用值超过信用界限，则可以注册会员，并选择会员类型
		if (customerVO.credit >= memberLevelVO.creditBoundries[1]) {
			// 若用户未选择会员类型，返回有信息空白
			if (memberLevelVO.memberType == null) {
				return ResultMessage.Blank;
			}
			// 若用户选择成为普通会员
			else if (memberLevelVO.memberType.equals("NORMAL")) {
				// 若用户未填写生日，返回有信息空白
				if (memberLevelVO.birthday == null) {
					return ResultMessage.Blank;
				}
				// 若用户生日填写为12.25，则返回注册成功
				else if (memberVO.birthday.equals(12.25)) {
					return ResultMessage.SignUp_Success;
				}
			}
			// 若用户选择成为企业会员
			else if (memberLevelVO.MemberType.equals("ENTREPRENEUR")) {
				// 若用户未填写企业名，返回有信息空白
				if (memberVO.companyName == null) {
					return ResultMessage.Blank;
				}
				// 若用户企业名填写为江南皮革厂，则返回注册成功
				else if (memberVO.companyName.equals("江南皮革厂")) {
					return ResultMessage.SignUp_Success;
				}
			}
		} else {
			return ResultMessage.Credit_Not_Enough;
		}
	}

	/**
	 * 会员升级
	 */
	@Override
	public void Upgrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO) {
		// 若用户为会员一级而信用值超过会员2级，则升级
		if (memberVO.level == 1 && customerVO.credit >= memberLevelVO.creditBoundries[2]) {
			memberVO.level++;
		}
	}

	/**
	 * 会员降级
	 */
	@Override
	public void Degrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO) {
		// 若用户为二级而信用值不足，则降级
		if (memberVO.level == 2 && customerVO.credit <= memberLevelVO.creditBoundries[2]
				&& customerVO.credit >= memberLevelVO.creditBoundries[1]) {
			customerVO.level--;
		}
	}

	/**
	 * 获取会员
	 */
	@Override
	public MemberVO getSingle(String ID) {
		if (ID.equals("12358")) {
			return memberVO;
		} else if (ID.equals("00000")) {
			return null;
		}
	}

}
