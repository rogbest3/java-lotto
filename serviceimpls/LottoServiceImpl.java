package com.lotto.web.serviceimpls;

import java.util.Random;

import com.lotto.web.daoimpls.LottoDAOImpl;
import com.lotto.web.daos.LottoDAO;
import com.lotto.web.domains.LottoBean;
import com.lotto.web.services.LottoService;

public class LottoServiceImpl implements LottoService {

	LottoDAO ldao;
	public LottoServiceImpl() {
		ldao = new LottoDAOImpl();
	}
	@Override
	public void createLotto(LottoBean param) {
		param.setBall(createBall());
		param.setLotteryNum(createLotteryNum());
		param.setLottoSeq(createLottoSeq());
		
		ldao.insertLotto(param);
	}
	public String createLottoSeq() {
		String seq = "No.";
		Random random = new Random();

		for(int i=0; i<4; i++) {
			seq += random.nextInt(10) + "";
		}
		System.out.println(seq);
//		seq = String.format("No.%04d", random.nextInt(9999)+1);
		return seq;
	}
	public String createBall() {
	
		return new Random().nextInt(45)+1+"";
	}
	public String createLotteryNum() {
		String lotteryNum = "";
		int[] arr = new int[6];
		int a = 0;

		for(int i = 0; i<arr.length; i++) {
			a = Integer.parseInt(createBall());
			if(duplicatePrevention(a, arr)) {
				i--;
			}else {
				arr[i] = a;
				lotteryNum += arr[i];
				if(i != 5 ) {
					lotteryNum += ",";
				}
			}
		}
	//	System.out.println(lotteryNum);
		
		lotteryNum = ascendingSort(arr);
		
	//	System.out.println(lotteryNum);
		return lotteryNum;
	}
	@Override
	public boolean duplicatePrevention( int a, int[] arr ) {
		boolean flag = false;
		for(int i = 0; i<arr.length; i++) {
			if(a == arr[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	@Override
	public String ascendingSort(int[] arr) {
		String result = "";
		int temp = 0;
		
		for(int i = 0; i<arr.length-1; i++) {
			for(int j = i+1; j<arr.length; j++) {
				if(arr[i] > arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					j=i;
				}
			}
		}
		
	for(int i = 0; i<arr.length; i++) {
			result += arr[i];
			if(i != 5 ) {
				result += ",";
			}
		}
		return result;
	}
}
