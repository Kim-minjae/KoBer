package controller;

import model.DriverDAO;
import model.DriverDTO;
import model.PassengerDAO;
import model.PassengerDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by pose2 on 2017-08-25.
 */
public class MainController {
	public static void main(String args[]) throws IOException {

		DriverDAO driverDAO = new DriverDAO();
		PassengerDAO passengerDAO = new PassengerDAO();
		DriverController dc = new DriverController();
		PassengerController psc=new PassengerController();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean session = true;

		Loop1: while (true) {
			System.out.println("다음 메뉴를 골라주세요 : ");
			System.out.println("1.회원가입 ");
			System.out.println("2.로그인 ");
			System.out.println("3.어플리케이션 종료");
			String tmp = br.readLine();
			if (!tmp.equals("1") && !tmp.equals("2")) {
				continue Loop1;
			} else {

				sign_in: switch (tmp) {
				case "1":
					// 회원가입 호출

					System.out.println("1.운전자로 회원등록");
					System.out.println("2.탑승자로 회원등록");

					st = new StringTokenizer(br.readLine());

					int switcher = Integer.parseInt(st.nextToken());

					switch (switcher) {
					case 1:
						try {
							DriverController driverService = new DriverController();
							driverService.DriverRegisterService();
							driverService.carInfoUpdate();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						continue Loop1;
					case 2:
						//회원가입 절차 
						 PassengerController passengerService = new PassengerController();
						 passengerService.passengerInsertService();
					}

					break Loop1;
				case "2":
					// 로그인 호출
					System.out.println("전화번호와 이름을 순서대로 입력해주세요 : <ex) 01011112222 홍길동>");
					st = new StringTokenizer(br.readLine());

					String phone_num_tmp = st.nextToken();
					String user_name_tmp = st.nextToken();

					int driver_id = 0;
					int passenger_id = 0;

					try {
						driver_id = driverDAO.D_login(phone_num_tmp, user_name_tmp);
						passenger_id = passengerDAO.P_login(phone_num_tmp, user_name_tmp);

						if (driver_id == -1) {
							if (passenger_id == -1) {
								System.out.println("해당하는 유저가 없습니다. 다시 메인화면으로 돌아갑니다.");
								continue Loop1;
							} else {
								System.out.println("탑승자 계정으로 로그인하셨습니다. ");
								PassengerDTO passengerDTO =passengerDAO.getPassenger(passenger_id);
								psc.passenger_menu(passengerDTO, passenger_id);
							
							}
						} else {
							if (passenger_id == -1) {
								System.out.println("운전자 계정으로 로그인하셨습니다. ");
								// 운전자 id를 불러오고 운전자 서비스를 시작함.
								DriverDTO driverDTO = driverDAO.getDriverByID(driver_id);
								dc.menu(driverDTO, driver_id);
							} else {
								System.out.println("탑승자/운전자 계정중 어느 계정으로 서비스를 시작하시겠습니까? ");
								st = new StringTokenizer(br.readLine());

								Loop2: switch (st.nextToken()) {
								case "1":
									System.out.println("탑승자 계정으로 로그인하셨습니다. ");
									PassengerDTO passengerDTO =passengerDAO.getPassenger(passenger_id);
									psc.passenger_menu(passengerDTO, passenger_id);

									break Loop1;
								case "2":
									System.out.println("운전자 계정으로 로그인하셨습니다. ");
									// 운전자 id를 불러오고 운전자 서비스를 시작함.
									DriverDTO driverDTO = driverDAO.getDriverByID(driver_id);
									dc.menu(driverDTO, driver_id);
									break Loop1;
								default:
									System.out.println("입력형식이 잘못되었습니다. 다시 입력해주세요");
									break Loop2;
								}

							}
						}

						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
					break Loop1;
				case "3":
					System.out.println("서비스를 종료합니다. -Bye-");
					return;
				default:
					System.out.println("맞는 케이스가 없습니다.");
					continue Loop1;
				}
			}
		}

	}
}
