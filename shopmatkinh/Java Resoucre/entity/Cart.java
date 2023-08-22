package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Item> items;	
	private int size;
	public Cart () {
		this.items = new ArrayList<>();
	}
	public Cart(List<Item> items) {
		this.items =  items;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Item getItemById(int id) {
		for(Item i : items) {
			if(i.getSanpham().getMasp() == id)
				return i;
		}
		return null;
	}
	public int getQuantityById(int id) {
		return getItemById(id).getLuongMua();
	}
	
	public void addItem(Item item){
		if(getItemById(item.getSanpham().getMasp())!=null) {
			Item oldI = getItemById(item.getSanpham().getMasp());
			oldI.setLuongMua(oldI.getLuongMua() + item.getLuongMua());
			oldI.setGia(oldI.getSanpham().getGiasp()*oldI.getLuongMua());
		}else
			items.add(item);
	}
	public void removeItem(int id) {
		if(getItemById(id) != null)
			items.remove(getItemById(id));
	}
	
	public double getTotalMoney() {
		double total = 0;
		for(Item i : items) {
			total += (i.getLuongMua()*i.getSanpham().getGiasp());
		}
		return total;
	}
	
	public int getSize() {
		int total = 0;
		for(Item i : items) {
			total += i.getLuongMua();
		}
		return total;
	}
	
	public void setSize() {
		this.size = getSize();
	}
}
