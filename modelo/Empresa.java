package modelo;
import java.util.ArrayList;

public class Empresa {
    //Vectores para cargar clientes y vigilantes.
    private ArrayList<Vigilante> vigilantes= new ArrayList<Vigilante>();
    private ArrayList<ClienteResidencia> clienteR= new ArrayList<ClienteResidencia>();
    private ArrayList<ClienteTienda> clienteT= new ArrayList<ClienteTienda>();
    //Metodos para cargar vectores.
    public void incluirVigilante(Vigilante vigi){
        vigilantes.add(vigi);
    }
    public void incluirClienteR(ClienteResidencia clir){
        clienteR.add(clir);
    }
    public void incluirClienteT(ClienteTienda clit){
        clienteT.add(clit);
    }
    //Este metodo es solo de las residencias
    public void asigarVigilantesReseidencia(){
        int cantidad=0;
        String ubicacion="";
        for(int i=0;i<clienteR.size();i++){
            cantidad=clienteR.get(i).getCantVigilantes();
            ubicacion=clienteR.get(i).getDireccion();               
            asignarTurnoVigilanteResidencia(cantidad,ubicacion,i);
        }
    }        
	//Metodo para asignar aleatoriamente los turno semanales a todos los vigilantes.
    public void asignarTurnoVigilanteResidencia(int cantidad, String ubicacion,int posicion){
        for(int i=0;i<7;i++){                   //Los 7 Dias de la semana
            for(int j=0;j<cantidad*2;j++){      //la cantidad de vigilantes por 2 xq son dos turnos por dia
                for(int k=0;k<vigilantes.size();k++){ //aqui estoy buscando a los vigilantes
                    if(j<cantidad){                     //esto es para los del turno de la mañana
                        if(vigilantes.get(k).getTurnos(i)==true && vigilantes.get(k).disAsignados()<5){ //reviso si el vigilante en esa posicion tiene ese dia disponible y no tiene mas de 5 dias asignados
                            Turno t = new Turno(ubicacion, clienteR.get(posicion).getHorario1()); // creo una instancia de turno
                            vigilantes.get(k).agregarTurno(t, i); //lleno el vector de turnos en el vigilante.
                        }
                    }else{// esto es lo mismo pero para los del turno de la noche
                        if(vigilantes.get(k).getTurnos(i)==true && vigilantes.get(k).disAsignados()<5){
                            Turno t = new Turno(ubicacion, clienteR.get(posicion).getHorario2());
                            vigilantes.get(k).agregarTurno(t, i);
                        }
                    }
                }
            }	
        }
    }
    public void asigarVigilantesTienda(){
        int cantidad=0;
        String ubicacion="";
        for(int i=0;i<clienteT.size();i++){
            cantidad=clienteT.get(i).getCantVigilantes();
            ubicacion=clienteT.get(i).getDireccion();               
            asignarTurnoVigilanteResidencia(cantidad,ubicacion,i);
        }
    }        
	//Metodo para asignar aleatoriamente los turno semanales a todos los vigilantes.
    public void asignarTurnoVigilanteTienda(int cantidad, String ubicacion,int posicion){
        for(int i=0;i<7;i++){
            for(int j=0;j<cantidad*2;j++){
                for(int k=0;k<vigilantes.size();k++){//este es igual al otro solo que tengo un solo turno
                    if(vigilantes.get(k).getTurnos(i)==true && vigilantes.get(k).disAsignados()<5){
                        Turno t = new Turno(ubicacion, clienteT.get(posicion).getHorario());
                        vigilantes.get(k).agregarTurno(t, i);
                    }
                }
            }
        }	
    }
}