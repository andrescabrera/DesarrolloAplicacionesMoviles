
	private static final String TAG = "Principal";
	private static final String PREFS_NAME = "PreferenciasREST";
	private RestAPIService restAPIService = new RestAPIService();
	private Button buttonBuscarPorId;
	private Button buttonGuardar;
	private EditText editTextIdBuscar;
	private EditText editTextNombre;
	private EditText editTextServer;
	private TextView textViewNombre;
	private TextView textViewId;
	private ProgressDialog progressDialog;
	private Interest interes;
	private Spinner spinnerCategorias;

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("server", editTextServer.getText().toString());
		editor.commit();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		buttonBuscarPorId = (Button) findViewById(R.id.buttonBuscarPorId);

		buttonGuardar = (Button) findViewById(R.id.buttonGuardar);
		editTextIdBuscar = (EditText) findViewById(R.id.editTextIdBuscar);
		editTextNombre = (EditText) findViewById(R.id.editTextNombre);
		editTextServer = (EditText) findViewById(R.id.editTextServer);
		textViewNombre = (TextView) findViewById(R.id.textViewNombre);
		textViewId = (TextView) findViewById(R.id.textViewId);
		spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);
		
		SharedPreferences prefs = getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		String server = prefs.getString("usuario", "Sin Definir");
		editTextServer.setText(server);
		restAPIService.setServer(server);

		editTextServer.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				restAPIService.setServer(editTextServer.getText().toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		buttonBuscarPorId.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				visualizarDatos();
			}
		});

		buttonGuardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				guardarDatos();
			}
		});
		
		spinnerCategorias.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				cargarSpinner();
				return false;
			}
		});
		
	}

	private void cargarSpinner() {
		List<Interest> intereses = restAPIService
				.recuperarIntereses();

		ArrayAdapter<CategoriaProducto> adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, intereses);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerCategorias.setFocusableInTouchMode(true);

		spinnerCategorias.setAdapter(adapter);
	}

	private void guardarDatos() {
		interes = null;

		progressDialog = ProgressDialog.show(this, "Trabajando..",
				"Recuperando datos", true, false);

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					interes = new Interest();
					interes.setDescription(editTextNombre.getText()
							.toString());
					Long id = restAPIService
							.crearInteres(interes);
					handlerGeneral.sendEmptyMessage(RESULT_OK);
				} catch (Exception e) {
					Log.e(TAG,
							"Error en la llamada JSON", e);
					handlerGeneral.sendEmptyMessage(RESULT_CANCELED);
				}

			}
		});
		thread.start();
	}

	private void visualizarDatos() {
		interes = null;

		progressDialog = ProgressDialog.show(this, "Trabajando..",
				"Recuperando datos", true, false);

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					long id = Long.parseLong(editTextIdBuscar.getText()
							.toString());
					interes = restAPIService
							.buscarInteresPorId(id);

					handlerGeneral.sendEmptyMessage(RESULT_OK);
				} catch (Exception e) {
					Log.e(TAG,"Error en la llamada JSON", e);
					handlerGeneral.sendEmptyMessage(RESULT_CANCELED);
				}

			}
		});
		thread.start();
	}

	private void visualizarAlert(String titulo, String msg) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false).setTitle(titulo)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private Handler handlerGeneral = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == RESULT_OK) {
				if (interes != null) {
					textViewId.setText(interes.getId().toString());
					textViewNombre.setText(interes.getDescription());
				}
			} else if (msg.what == RESULT_CANCELED) {
				visualizarAlert("Error", "Error en la llamada JSON");
			}

			progressDialog.dismiss();

		}
	};
