    static class ponto_t {
    
        int x, y;
    }
    
    static ponto_t[] p, q;
    static ponto_t origem;
    static boolean[] v;
    
    static int produtoVetorial(ponto_t a, ponto_t b, ponto_t c) {
        ponto_t p1 = new ponto_t(), p2 = new ponto_t();
        p1.x = b.x - a.x;
        p1.y = b.y - a.y;
        p2.x = c.x - a.x;
        p2.y = c.y - a.y;
    
        return p1.x * p2.y - p2.x * p1.y;
    }
    
    static int dist2(ponto_t a, ponto_t b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    
    static int compara(ponto_t c, ponto_t d) {
        int e;
    
        e = produtoVetorial(origem, c, d);
    
        if (e == 0) {
            if (dist2(origem, c) > dist2(origem, d)) return 1;
    
            return -1;
        }
    
        if (e < 0) return 1;
    
        return -1;
    }
    
    /*assume que o poligono estah em p[] e poe o convexHull em q[]*/
    /*retorna o numero de pontos em q[]*/
    static int convexHull(int n) {
        int m = 0; /*escolhe origem*/
        v = new boolean[n];
    
        for (int i = 1; i < n; i++)
            if (p[i].y < p[m].y || (p[i].y == p[m].y && p[i].x < p[m].x)) m = i;
    
        ponto_t aux = p[0];
        p[0] = p[m];
        p[m] = aux;
        origem = p[0]; /*fim de escolhe origem*/
    
        Arrays.sort(p, new Comparator<ponto_t>() {
    
            public int compare(ponto_t o1, ponto_t o2) {
                return compara(o1, o2);
            }
    
        });
    
        for (int i = 0; i < n; i++)
            /*elimina colineares*/
            v[i] = true;
    
        for (int i = 1; i < n - 1; i++)
            if (produtoVetorial(p[i - 1], p[i], p[i + 1]) == 0) v[i] = false;
    
        int j = 0;
    
        for (int i = 0; i < n; i++)
            if (v[i]) q[j++] = p[i];
    
        n = j;
    
        for (int i = 0; i < n; i++)
            p[i] = q[i]; /*fim de elimina colineares*/
    
        int topo = 0; /*inicializa solucao do convexHull*/
    
        for (int i = 0; i < 3; i++)
            q[topo++] = p[i];
    
        for (int i = 3; i < n; i++) { /*graham-scan*/
            while (produtoVetorial(q[topo - 2], q[topo - 1], p[i]) < 0)
                topo--;
    
            q[topo++] = p[i];
        }
        return topo;
    }