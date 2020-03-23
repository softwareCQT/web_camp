/**
 * 
 */
package model;

/**
 * @author 陈起廷
 * @version 2019年5月5日
 */
public class ShowCart {
	/**
	 * 调用其中的set方法来获得想要展示到购物车的数据
	 * 其中需要的购物车表单数据为 数量和加入购物车的时间
	 */
      private Shopcart Cart;
      /**
       * 需要具体的商品信息
       */
      private Commodity Good;
      public ShowCart(Shopcart cart, Commodity good)
      {
    	  Cart = cart;
    	  Good = good;
      }
	public Shopcart getCart() {
		return Cart;
	}
	public void setCart(Shopcart cart) {
		Cart = cart;
	}
	public Commodity getGood() {
		return Good;
	}
	public void setGood(Commodity good) {
		Good = good;
	}
}
