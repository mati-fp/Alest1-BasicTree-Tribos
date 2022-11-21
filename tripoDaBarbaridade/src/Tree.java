public class Tree {
    
    //NODO BASICO PARA A CONSTRUÇÃ DA ÁRVORE
    private class TreeNode{
        public String nome;
        public TreeNode father;
        private TreeNode [] children;
        private int nChild;
        public int terras;
        public int nLinhagem;

        public TreeNode (String guerreiro, Integer element){
            father=null;
            children=new TreeNode[10];
            nome=guerreiro;
            terras=element;
            nChild=0;
        }
        public void addSubtree (TreeNode n){
            if(nChild==children.length)
                grow();
            children[nChild] = n;
            n.father=this;
            nChild++;
            n.nLinhagem = this.nLinhagem + 1;
        }
        private void grow(){
            TreeNode aux [] = new TreeNode[children.length*2];
            for(int i=0; i<children.length; i++)
              aux[i]=children[i];
            children=aux;            
        }
        public boolean comparaNome(String nome){
            if (nome == this.nome)
                return true;

            return false;
        }
        public boolean removeSubtree(TreeNode n){
            if(n==null)
                return false;
            
            int idx=-1;
            for(int i=0; i<nChild; i++)
                if(children[i]==n){
                    idx=i;
                    break;
                }
            
            if(idx==-1)
                return false;

            for(int i=idx; i<nChild; i++)
                children[i]=children[i+1];
            nChild--;
            children[nChild]=null;
            
            return true;
        }
        // busca subtree pelo indice dentro da lista de filhos
        public TreeNode getSubtree(int i){
            if(i>=0 && i<nChild)
              return children[i];
            return null;
        }
        //retorna o número de filhos
        public int getSubtreeSize(){
            return nChild;
        }  
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // ATRIBUTOS E MÉTODOS DA ÁRVORE
    private TreeNode root;
    private int nElements;

    public Tree(){
        this.root=null;
        this.nElements=0;
    }

        // método privado elaborado na versão 0.2
        private TreeNode searchNode(String value, TreeNode ref){

            if(ref!=null){
                if(ref.nome.equals(value))
                    return ref;
                else{
                    TreeNode aux=null;
                    for(int i=0; i<ref.getSubtreeSize(); i++){
                        aux=searchNode(value, ref.getSubtree(i));
                        if(aux != null){
                            return aux;
                        }
                    }
                    return null;
                }
            }
            else
                return null;
    
        }
    
        //insere o elemento e como filho de father
        // Versao 0.1 -> Inclui root e inclui filho de root
        // Versao 0.2 -> Permite a inclusão de mais níveis na árvore
        public boolean add(String father, String son, Integer terras){
            TreeNode aux;
            if(nElements==0){
                this.root=new TreeNode(son, terras);
                root.nLinhagem = 1;
            }
            else{
                aux = searchNode(father, root);
                if(aux==null)
                    return false;
                else
                    aux.addSubtree(new TreeNode(son, terras));
                    
            }
            nElements++;
    
            return true;
        }

        public void distribuiHeranca(){
            
            herancaDoPapai(root);

        }

        public boolean herancaDoPapai(TreeNode VeioDaLancha){
            
            TreeNode descendenteMaisRico;
            TreeNode filhoSortudo;
            int herancaPaizao = (VeioDaLancha.terras)/VeioDaLancha.getSubtreeSize();
            for (int i =0; i < VeioDaLancha.getSubtreeSize(); i++){
                filhoSortudo = VeioDaLancha.getSubtree(i);
                filhoSortudo.terras += herancaPaizao;
                if (filhoSortudo.getSubtreeSize() > 0)
                    herancaDoPapai(filhoSortudo);
                
                
            }

            return true;
        }

        public void printCasoTeste(){

        }

        public void printDescententeRicasso(){


        }
    
        //retorna o elemento armazenado na raiz
        public String getRoot(){
            if(root!=null)
                return root.nome;
            return null;
        }
    
        //altera o elemento armazenado na raiz
        public void setRoot(String e){
            if((e!=null) && (root!=null)){
                root.nome=e;
            }        
        }
    
        //retorna o pai do elemento e
        public String getParent(String e){
            TreeNode aux=searchNode(e, this.root);
            if((aux!=null)&&(aux.father!=null))
                return aux.father.nome;
            return null;
        }
    
        //remove o elemento e e seus filhos
        public boolean removeBranch(String e){
            TreeNode aux = searchNode(e, root);
            if(aux == null)
                return false;
    
            if(aux == this.root)
                clear();
            else{
                aux.father.removeSubtree(aux);
                // ATUALIZAR O NELEMENTS (BOA SORTE!!!)
            }
    
            return true;
        }
    
        //retorna true se a árvore contém o elemento e    
        public boolean contains(String e){
            return (searchNode(e, this.root)!=null);
        }
    
        //retorna true se o elemento está armazenado em um nodo interno
        public boolean isInternal(String e){
            TreeNode aux = searchNode(e, this.root);
            if((aux!=null)&&(aux.getSubtreeSize()>0))
                return true;
            return false;
        }
    
        //retorna true se o elemento está armazenado em um nodo externo
        public boolean isExternal(String e){
            TreeNode aux = searchNode(e, this.root);
            if((aux!=null)&&(aux.getSubtreeSize()==0))
                return true;
            return false;
        }
    
        //retorna true se o elemento e está armazenado na raiz
        public boolean isRoot(String e){
            if((root!=null)&&(e!=null)&&(root.nome==e))
                return true;
            return false;
        }
    
        //retorna true se a árvore está vazia    
        public boolean isEmpty(){
            return (nElements==0);
        }
    
        //retorna o número de elementos armazenados na árvore
        public int size(){
            return nElements;
        }
        //remove todos os elementos da árvore
        public void clear(){
            //requer navegação
            //ou solução baseada em garbage collector
            this.root=null;
            this.nElements=0;
        }
    
        //retorna uma lista com todos os elementos da árvore com um caminhamento em largura
        public String [] positionsWidth(){
            if(nElements==0)
                return null;
            
            String [] lista = new String[this.nElements];
            int idx=0;
            int pos=0;
    
            lista[idx++]=root.nome;
            while(idx<nElements){
                TreeNode aux = searchNode(lista[pos++], this.root);
                if(aux!=null)
                    for(int i=0; i<aux.getSubtreeSize(); i++)
                        lista[idx++]=aux.getSubtree(i).nome;
            }
    
            return lista;
    
        }
    
        public void doTheString(){
    
            printValue(root);
    
        }
    
        private void printValue(TreeNode ref){
    
            if(ref!=null){
                System.out.print(ref.nome+"; ");
                for(int i=0; i<ref.getSubtreeSize(); i++)
                    printValue(ref.getSubtree(i));
            }
    
        }


}
