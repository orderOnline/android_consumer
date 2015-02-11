package com.invsol.getfoodyc.models;

public class ModelFacade {
	public static final String TAG = "Model Facade";
	/**
	 * Remote Model Reference to handle Network data and function calls
	 */
	private RemoteModel remoteModel;
	private ConnectionModel connModel;
	private LocalModel localModel;
	private CustomerModel customerModel;
	private OrdersModel orderModel;
	private ChatModel chatModel;
	private RestaurantsModel restaurantsModel;
	// ---------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	public ModelFacade() {
		// Initializing Remote Model
		remoteModel = new RemoteModel();
		connModel = new ConnectionModel();
		localModel = new LocalModel();
		customerModel = new CustomerModel();
		orderModel = new OrdersModel();
		chatModel = new ChatModel();
		restaurantsModel = new RestaurantsModel();
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Returns Remote Model Reference
	 * 
	 * @return RemoteModel
	 */
	public RemoteModel getRemoteModel() {
		return remoteModel;
	}

	public ConnectionModel getConnModel() {
		return connModel;
	}

	public LocalModel getLocalModel() {
		return localModel;
	}

	public CustomerModel getCustomerModel() {
		return customerModel;
	}

	public OrdersModel getOrderModel() {
		return orderModel;
	}

	public ChatModel getChatModel() {
		return chatModel;
	}

	public RestaurantsModel getRestaurantsModel() {
		return restaurantsModel;
	}

	
	
	// ---------------------------------------------------------------------------------

}