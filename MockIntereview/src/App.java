import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		int idNow = 1;
		int cnt = 1;
		List<Room> listRooms = new ArrayList<Room>();
		List<Booking> listItemBookings = new ArrayList<Booking>(); 
		while(num!=0) {
			num--;
			String oprString = sc.next();
			
			if(oprString.equals("create")) {
				sc.next();
				oprString = sc.next();
				listRooms.add(new Room(oprString, idNow));
				idNow++;
				
			}
			else if(oprString.equals("book")) {
				int id = sc.nextInt();
				String nameRoom="";
				for(Room x:listRooms) {
					if(x.getId()==id) {
						nameRoom = x.getName();
					}
				}
				int st = sc.nextInt();
				int en = sc.nextInt();
				boolean flag = true;
				for(Booking item : listItemBookings) {
					if(item.getName().equals(nameRoom)) {
						if((st>=item.getStartDate() && st<=item.getEndDate()) && (en>=item.getStartDate() && en<=item.getEndDate() )) {
							flag = false;
							break;
						}
					}
				}
				if(flag==true) {
					listItemBookings.add(new Booking(cnt, nameRoom, st, en));
					cnt++;
				}
			}
			else if(oprString.equals("cancel")) {
				int cancelId = sc.nextInt();
				listItemBookings.remove(cancelId-1);
			}
			
		}
		sc.close();
		for(int i=0;i<listRooms.size();i++) {
			String room = listRooms.get(i).getName();
			System.out.println("Room: "+room);
			for(Booking item: listItemBookings) {
				if(item.getName().equals(room)) {
					System.out.println("Booking Id "+item.getId()+": "+item.getStartDate()+" -> "+item.getEndDate());
				}
			}
			System.out.println();
		}
	}
}
/*
7
create room Suite
book 1 5 10
create room Deluxe
book 2 1 10
book 1 12 18
book 2 20 25
cancel 4
*/