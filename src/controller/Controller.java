package controller;

import java.util.Scanner;

import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.YoutubeVideo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run()
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){

			//Menu para escoger la representacion
			view.printMenuCarga();
			int optionCarga = lector.nextInt();
			switch (optionCarga) {
			case 1:
				view.printMessage("Cargando en arreglo dinamico ");
				modelo.cargarVideosDinamico(); 
				modelo.cargarCategorias();
				int sizeVideosDinamico = modelo.darVideosDinamico().size();
				int sizeCategoriasDinamico = modelo.darCategoriasVideos().size();
				view.printMessage("Cantidad de registros de videos cargados: " + sizeVideosDinamico);
				view.printMessage("Cantidad de registros de categorias cargados: " + sizeCategoriasDinamico);

				view.printMenuRequerimientos();
				int optionReq = lector.nextInt();
				switch (optionReq){
				case 1:
					view.printMessage("Sobre cual categoria desea aplicar el requerimiento?");
					String categoryNameR1 = lector.next();
					view.printMessage("Sobre cual pais desea aplicar el requerimiento?");
					String countryR1 = lector.next();
					view.printMessage("Cuantos videos con el mayor numero de reproducciones desea tener?");
					int numVideos = lector.nextInt();
					//
					long timereq1= System.currentTimeMillis();
					ILista<YoutubeVideo> subListaPaisCategoria = modelo.Req1(categoryNameR1, countryR1);
					view.printMessage("Los " +numVideos+ " videos tendencia en "+countryR1+ " con mas views de la categoria "+categoryNameR1+" son:");
					view.printMessage("numero de videos de consulta"+subListaPaisCategoria.size());
					for (int i = 1; i <= numVideos && i<=subListaPaisCategoria.size(); i++)
					{
						view.printMessage("-----------------------------------------" );
						
						view.printMessage("trendingDate: " +subListaPaisCategoria.getElement(i).getTrendingDate());
						view.printMessage("title: " +subListaPaisCategoria.getElement(i).getTitle());
						view.printMessage("channelTitle: " +subListaPaisCategoria.getElement(i).getChannelTitle());
						view.printMessage("publishTime: " +subListaPaisCategoria.getElement(i).getPublishTime());
						view.printMessage("views: " +subListaPaisCategoria.getElement(i).getViews());
						view.printMessage("likes: " +subListaPaisCategoria.getElement(i).getLikes());
						view.printMessage("dislikes: " +subListaPaisCategoria.getElement(i).getDislikes());
					}
					long stimereq1 = System.currentTimeMillis();
					long delta1= stimereq1-timereq1;
					view.printMessage("tiempo en ms= "+delta1);
					//
					break;
				case 2:
					view.printMessage("Sobre cual pais desea aplicar el requerimiento?");
					String countryR2 = lector.next();
					long timereq2= System.currentTimeMillis();
					modelo.Req2(countryR2);
					long stimereq2 = System.currentTimeMillis();
					long delta2= stimereq2-timereq2;
					view.printMessage("tiempo en ms= "+delta2);
					break;
				case 3:
					view.printMessage("Sobre cual categoria desea aplicar el requerimiento?");
					String categoryNameR3 = lector.next();
					long timereq3= System.currentTimeMillis();
					modelo.Req3(categoryNameR3);
					long stimereq3 = System.currentTimeMillis();
					long delta3= stimereq3-timereq3;
					view.printMessage("tiempo en ms= "+delta3);
					break;
				case 4:
					view.printMessage("Sobre cual tag desea aplicar el requerimiento?");
					String tag = lector.next();
					view.printMessage("Sobre cuantos videos desea aplicar el requerimiento?");
					int b = lector.nextInt();
					long timereq4= System.currentTimeMillis();
					modelo.Req4(tag,b);
					long stimereq4 = System.currentTimeMillis();
					long delta4= stimereq4-timereq4;
					view.printMessage("tiempo en ms= "+delta4);
					break;
				case 5:
					fin = true;
					break;
				}
			case 2: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;
			}
		}
	}	
}