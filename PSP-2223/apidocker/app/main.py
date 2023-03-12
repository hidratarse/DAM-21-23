from fastapi import FastAPI

app = FastAPI()


@app.get("/")
async def root():
    return {"resultado": "Hello World"}

@app.get("/suma/{n1}/{n2}/")
async def sum(n1: int, n2: int):
    s = n1 + n2
    return{"resultado":s}

@app.get("/multiplicacion/{n1}/{n2}/")
async def sum(n1: int, n2: int):
    s = n1 * n2
    return{"resultado":s}

@app.get("/resta/{n1}/{n2}/")
async def sum(n1: int, n2: int):
    s = n1 - n2
    return{"resultado":s}

@app.get("/division/{n1}/{n2}/")
async def sum(n1: int, n2: int):
    s = n1 / n2
    return{"resultado":s}