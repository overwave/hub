import useSWR from "swr";

const fetcher = (url: string) => fetch(url).then((res) => res.json());

type BoardDto = {
    board: Map<string, TileDto>
}

type TileDto = {
    address: string,
    figure: FigureDto,
}

export type FigureDto = {
    color: 'WHITE' | 'BLACK',
    type: 'PAWN' | 'KNIGHT' | 'BISHOP' | 'ROOK' | 'QUEEN' | 'KING',
}

function getHost(): string {
    return typeof window === 'undefined' ?
        '' :
        localStorage.getItem("local") ? 'http://localhost:8081' : '';
}

export function useBoard(): { board: BoardDto, isLoading: boolean, error: any } {
    let {data, error, isLoading} = useSWR(getHost() + '/chess/api/game/board', fetcher);
    return {
        board: data,
        isLoading,
        error
    };
}