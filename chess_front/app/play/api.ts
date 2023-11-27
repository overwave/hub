import useSWR from "swr";

const fetcher = (url: string) => fetch(url).then((res) => res.json());

export type BoardDto = {
    board: Map<string, TileDto>
}

export type TileDto = {
    address: string,
    figure: FigureDto,
}

export type FigureDto = {
    color: 'WHITE' | 'BLACK',
    type: 'PAWN' | 'KNIGHT' | 'BISHOP' | 'ROOK' | 'QUEEN' | 'KING',
}

function getHost(): string {
    return typeof window === 'undefined' ?
        'https://overwave.dev' :
        localStorage.getItem("local") ? 'http://localhost:8081' : 'https://overwave.dev';
}

export function useBoard(): { board?: BoardDto, isLoading: boolean, error: any } {
    let {data, error, isLoading} = useSWR(getHost() + '/chess/api/game/board', fetcher);
    return {
        board: data ? {board : new Map(Object.entries(data.board))} : undefined,
        isLoading,
        error
    };
}
