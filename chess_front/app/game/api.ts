import useSWR from "swr";
import {getHost} from "@/app/utils";

class HttpError extends Error {
    body: any;
    status: number;

    constructor(body: any, status: number) {
        super(body?.message || 'An error occurred while fetching the data.');
        this.body = body;
        this.status = status;
    }
}

const fetcher = (url: string) => fetch(url, {
    credentials: 'include',
}).then(async (res) => {
    if (!res.ok) {
        throw new HttpError(await res.json(), res.status)
    }
    return res.json();
});

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

export function useBoard(): { board?: BoardDto, isLoading: boolean, error: any } {
    let {data, error, isLoading} = useSWR(getHost() + '/chess/api/game/board', fetcher);
    return {
        board: data ? {board: new Map(Object.entries(data.board))} : undefined,
        isLoading,
        error
    };
}

export type LobbyDto = {
    sessionRequests: SessionRequestDto[],
}

export type SessionRequestDto = {
    id: number,
    opponent: PlayerDto,
    opponentSide: 'WHITE' | 'BLACK' | 'ANY',
    status: any,
}

export type PlayerDto = {
    login: string,
    name: string,
    bot: boolean,
}

export function useWaitingLobby(): { lobby?: LobbyDto } {
    let {data} = useSWR(getHost() + '/chess/api/game/open', fetcher);
    return {lobby: data};
}

export type UserDto = {
    login: string,
}

export async function useUser(): Promise<UserDto | undefined> {
    return fetch(getHost() + "/chess/api/user/me", {
        credentials: 'include',
    }).then((response) => response.ok ? response.json() : Promise.resolve(undefined))
        .catch(() => undefined);
}
