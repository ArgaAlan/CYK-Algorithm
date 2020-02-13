/*
import java.lang.Object;
import java.util.Hashtable;
import java.util.LinkedList;
public class PalabrasRelacionadas {

	private String p1 = "",p2 = "", p3 = "";
	private palabraRelacionada root;
	private int size = 0;
	
	LinkedList<String> lista = new LinkedList<>();
	{
		lista.add("");
		lista.add("");
		lista.add("");
	}
	
	public String getP1() {return this.p1;}
	public void setP1(String p1) {this.p1 = p1;}

	public String getP2() {return this.p2;}

	public void setP2(String p2) {this.p2 = p2;}

	public String getP3() {return this.p3;}

	public void setP3(String p3) {
		this.p3 = p3;}



	public void acomodarPalabra(String palabra) {
		insert(new palabraRelacionada(palabra));
		if(palabra.equals(p1) || palabra.equals(p2) || palabra.equals(p3)) {
			
		}else {
			
			this.p1 = this.root.palabra;
			
			if(this.root.left != null) {
				this.p2 = this.root.left.palabra;
			}else {
				if(this.root.right != null) {
				this.p2 = this.root.right.palabra;
				}else {
					this.p2 = "";
				}
			}
			
			if(this.root.right != null) {
				if(!this.root.right.palabra.equals(p2)) {
					this.p3 = this.root.right.palabra;
				}else {
					if(this.root.right.right!=null && this.root.right.left==null) {
						this.p3 = this.root.right.right.palabra;
					}else if(this.root.right.left != null && this.root.right.right==null) {
						this.p3 = this.root.right.left.palabra;
					}else if(this.root.right.left != null && this.root.right.right!=null) {
						if(this.root.right.right.rep > this.root.right.right.rep) {
							this.p3 = this.root.right.right.palabra;
						}else if(this.root.right.right.rep < this.root.right.right.rep) {
							this.p3 = this.root.right.left.palabra;
						}else {
							this.p3 = this.root.right.left.palabra;
						}
					}else {
						this.p3 = "";
					}
				}
			}else if(this.root.left != null) {
				if(this.root.left.right!=null && this.root.left.left==null) {
					this.p3 = this.root.left.right.palabra;
				}else if(this.root.left.left != null && this.root.left.right==null) {
					this.p3 = this.root.left.left.palabra;
				}else if(this.root.left.left != null && this.root.left.right!=null) {
					if(this.root.left.right.rep > this.root.left.right.rep) {
						this.p3 = this.root.left.right.palabra;
					}else if(this.root.left.right.rep < this.root.left.right.rep) {
						this.p3 = this.root.left.left.palabra;
					}else {
						this.p3 = this.root.left.left.palabra;
					}
				}else {
					this.p3 = "";
				}
			}
			
		}
	}
	
	public void insert(palabraRelacionada nuevo) {
		palabraRelacionada curr = null;
		if(this.size == 0) {
			this.root = nuevo;
			this.size++;
		}else {
			curr = this.root;
			palabraRelacionada parent = this.root;
			boolean inserted = false;
			while(!inserted) {
				if(nuevo.palabra.compareTo(curr.palabra)<0){
					if(curr.left == null) {
						curr.setLeft(nuevo);
						nuevo.setParent(curr);
						inserted = true;
						this.size++;
					}else {
						parent = curr;
						curr = curr.left;
					}
				}else if(nuevo.palabra.compareTo(curr.palabra)>0) {
					if(curr.right == null) {
						curr.setRight(nuevo);
						nuevo.setParent(curr);
						inserted = true;
						this.size++;
					}else {
						parent = curr;
						curr = curr.right;
					}
				}else {
					curr.rep++;
					inserted = true;
					this.size++;
				}
			}
		}
		acomodar(curr);
	}
	
	public void acomodar(palabraRelacionada curr) {
		System.out.println(curr.getRep());
			while(curr != this.root) {
					if(curr.getRep() > curr.getParent().getRep()) {
						swap(curr, curr.getParent());
					}
					curr = curr.getParent();
				}
	}
	
	public void swap(palabraRelacionada curr, palabraRelacionada parent) {
		if((parent.left != null) && (parent.right != null)) {
			if(curr.palabra.equals(parent.left.palabra)) {
				curr.setParent(parent.getParent());
				curr.setRight(parent.right);
				parent.setParent(curr);
			}else {
				curr.setParent(parent.getParent());
				curr.setLeft(parent.left);
				parent.setParent(curr);
			}
		}else {
			curr.setParent(parent.getParent());
			parent.setParent(curr);
		}
	}
	
}
class palabraRelacionada {
	palabraRelacionada parent;
	palabraRelacionada left;
	palabraRelacionada right;
	String palabra;
	int rep;
	
	public palabraRelacionada(String palabra) {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.palabra = palabra;
		this.rep = 1;
	}
	public void setLeft(palabraRelacionada left) {
		this.left = left;
	}
	public void setRight(palabraRelacionada right) {
		this.right = right;
	}
	public void setParent(palabraRelacionada parent) {
		this.parent = parent;
	}
	public int getRep() {
		return this.rep;
	}
	public palabraRelacionada getParent() {
		return this.parent;
	}
}
*/
import java.util.Hashtable;
public class PalabrasRelacionadas {
	
	private String p1 = "",p2 = "", p3 = "";
	private Integer mayor = 0,medio = 0 ,menor = 0;
	Hashtable<String, Integer> palabras = new Hashtable<>();
	
	
	public String getP1() {return p1;}
	public void setP1(String p1) {this.p1 = p1;}

	public String getP2() {return p2;}

	public void setP2(String p2) {this.p2 = p2;}

	public String getP3() {return p3;}

	public void setP3(String p3) {
		this.p3 = p3;}



	public void acomodarPalabra(String palabra) {
		if(palabra.equals(p1) || palabra.equals(p2) || palabra.equals(p3)) {
			
		}else {
			if(!palabras.containsKey(palabra)) {
				palabras.put(palabra, 0);
				recorrerValores(palabra, palabras.get(palabra));
			}else {
				palabras.replace(palabra, palabras.get(palabra), palabras.get(palabra)+1);
				if(palabras.get(palabra)>=menor) {
					recorrerValores(palabra, palabras.get(palabra));
				}
			}
		}
	}
	
	public void recorrerValores(String palabra, int valor) {
		if(valor>=mayor) {
			menor = medio;
			medio = mayor;
			mayor = valor;
			p1 = p2;
			p2 = p3;
			p3 = palabra;
		}else if(valor>=medio){
			menor = medio;
			medio = valor;
			p1 = p2;
			p2= palabra;
		}else {
			menor = valor;
			p1 = palabra;
		}
	}	
}