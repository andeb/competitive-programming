#include <iostream>
#include <string>
using namespace std;
 
int instancia = 0;
 
int main()
{
        int entrada = 0;
        int matriz[9][9];
		
        cin >> entrada;
 
        for(int it = 0; it < entrada; it ++)
        {
			string saida = "";
		
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++) 
					cin >> matriz[i][j];
        
        int i, j, soma_linha[9], soma_coluna[9], soma_matriz[3][3];
 
        for (i = 0; i < 9; i++)
            soma_linha[i] = soma_coluna[i] = soma_matriz[i/3][i%3] = 0;
    
        for (i = 0; i < 9; i++)
            for (j = 0; j < 9; j++) 
			{           
                soma_linha[i] += matriz[i][j]*matriz[i][j];
                soma_coluna[j] += matriz[i][j]*matriz[i][j];
                soma_matriz[i/3][j/3] += matriz[i][j]*matriz[i][j];
			} 
        
        for (i = 0; i < 9; i++)
            if (soma_coluna[i] != 285 || soma_linha[i] != 285 || soma_matriz[i/3][i%3] != 285 )
                saida = "NAO";
                
        
		if(saida == "")
			saida  = "SIM";
		
        instancia ++;
        cout << "Instancia " << instancia << endl;		
		
        cout << saida << endl;
		cout << endl;
		
		saida = "";
	}
          
    return 0;
}